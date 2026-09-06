1. User Profile Header
   • Content: Display the user's name and email (from the /user login response).
   • Visual: A welcome message like "Hello, [Name]!" with their url_foto as an avatar.
2. System Status / Alerts
   • Content: A "Recent Alerts" or "System Health" section.
   • Source: Aggregated data from /logs for the user's nodes.
   • Value: Immediate visibility into "Unresolved" issues like "Low Battery" or sensor malfunctions across all devices without needing to drill down into each node.
3. Managed Clusters (Primary Navigation)
   • Content: A horizontal or vertical list of Clusters assigned to the user.
   • Visual: Use the icon and name of the cluster.
   • Source: Data from /cluster using the IDs found in the user's profile.
   • Value: Since clusters represent physical groupings (e.g., "TrapGradien TelU"), this is the most logical way for users to browse their devices.
4. Quick Look: Key Nodes
   • Content: A few "Pinned" or "Favorite" nodes showing their most recent status.
   • Visual: Use the icon and name of the node.
   • Status: Show the Purification Status (from field1 in the telemetry) formatted according to the config thresholds (e.g., displaying the "leaf" icon and "Ion Added" label if the value is 1).
   • Source: Data from /node and the latest entry from /read.
5. Geographical Overview (Map)
   • Content: A mini-map showing the locations of the user's clusters.
   • Source: latitude and longitude fields from the cluster details.
   • Value: Provides context for where devices are deployed, especially for "Stationary" clusters.